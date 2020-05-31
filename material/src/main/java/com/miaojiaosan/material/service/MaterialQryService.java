package com.miaojiaosan.material.service;

import com.miaojiaosan.material.cmd.qry.MaterialListQry;
import com.miaojiaosan.material.service.dto.MaterialDTO;
import com.miaojiaosan.material.service.dto.MaterialListDTO;
import com.miaojiaosan.material.service.dto.PageDTO;

/**
 * @author miaojiaosan
 */
public interface MaterialQryService {

  PageDTO<MaterialListDTO> draft(MaterialListQry qry);

  PageDTO<MaterialListDTO> list(MaterialListQry qry);

  MaterialDTO byId(Long id);

}
