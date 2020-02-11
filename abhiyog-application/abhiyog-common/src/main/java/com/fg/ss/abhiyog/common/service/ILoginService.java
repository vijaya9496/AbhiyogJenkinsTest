package com.fg.ss.abhiyog.common.service;

import com.fg.ss.abhiyog.common.vo.UserVO;

public interface ILoginService {

	UserVO validateUser(String loginId, String password);

}
