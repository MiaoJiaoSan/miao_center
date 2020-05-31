package com.miaojiaosan.material.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.material.cmd.qry.MaterialListQry;
import com.miaojiaosan.material.service.MaterialQryService;
import com.miaojiaosan.material.service.dto.MaterialDTO;
import com.miaojiaosan.material.service.dto.MaterialListDTO;
import com.miaojiaosan.material.service.dto.PageDTO;
import com.miaojiaosan.material.vo.MaterialListVO;
import com.miaojiaosan.material.vo.MaterialVO;
import com.miaojiaosan.material.vo.PageVO;
import com.miaojiaosan.utils.AccountUtil;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 *
 * @author miaojiaosan
 */
@RestController
@RequestMapping("/material/qry")
public class MaterialQryController {

  @Resource
  private MaterialQryService materialQryService;
  @Resource
  private HttpServletRequest httpServletRequest;
  @Resource
  private Mapper mapper;

  @GetMapping("/draft")
  public Result<PageVO<MaterialListVO>> draft(MaterialListQry qry){
    qry.setAccountId(AccountUtil.id(httpServletRequest));
    PageDTO<MaterialListDTO> pageDTO = materialQryService.draft(qry);
    PageVO<MaterialListVO> page = new PageVO<>(pageDTO.getList().stream()
        .map(materialDTO -> mapper.map(materialDTO,MaterialListVO.class))
        .collect(Collectors.toList()),pageDTO.getTotal());
    return Result.successful(page);
  }

  @GetMapping("/list")
  public Result<PageVO<MaterialListVO>> list(MaterialListQry qry){
    PageDTO<MaterialListDTO> pageDTO = materialQryService.list(qry);
    PageVO<MaterialListVO> page = new PageVO<>(pageDTO.getList().stream()
        .map(materialDTO -> mapper.map(materialDTO,MaterialListVO.class))
        .collect(Collectors.toList()),pageDTO.getTotal());
    return Result.successful(page);
  }

  @GetMapping("/id/{id}")
  public Result<MaterialVO> byId(@PathVariable Long id){
    MaterialDTO materialDTO = materialQryService.byId(id);
    return Result.successful(mapper.map(materialDTO, MaterialVO.class));
  }
}
