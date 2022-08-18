package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.service.model.params.AuthorizeVo
import club.smileboy.mlnlco.service.model.params.Param
import org.springframework.stereotype.Service

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 21:34
 * @description 认证服务
 **/

@Service
class AuthorizeService(private val optionService: OptionService) {
    /**
     * 授权 动作
     */
    fun authorize(param: Param): AuthorizeVo {

        // 拿到对应的app 的secret 进行 jwt 令牌解密 ...
    }

}