package club.smileboy.mlnlco.service.controller

import club.smileboy.mlnlco.service.model.params.AuthorizeVo
import club.smileboy.mlnlco.service.model.params.Param
import club.smileboy.mlnlco.service.service.AuthorizeService
import club.smileboy.mlnlco.service.util.AppResult
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 21:22
 * @description 授权端点
 *
 * 需要引入
 **/
@RestController
@RequestMapping("api/authorize/v1")
class AuthorizeController(private val authorizeService: AuthorizeService) {
    /**
     *
     * 支持 oauth2  /  username-password 认证令牌 ...
     * 进行认证 ....
     */
    @PostMapping("token.json", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun authorize(param: Param): AppResult<AuthorizeVo> {
        return authorizeService.authorize(param).let {
            AppResult.success(it)
        }
    }

}