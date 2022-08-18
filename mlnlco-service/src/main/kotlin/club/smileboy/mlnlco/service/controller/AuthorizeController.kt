package club.smileboy.mlnlco.service.controller

import club.smileboy.mlnlco.service.model.params.AuthorizeVo
import club.smileboy.mlnlco.service.model.params.Param
import club.smileboy.mlnlco.service.service.AuthorizeService
import club.smileboy.mlnlco.service.util.AppResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author JASONJ
 * @date 2022/8/17
 * @time 21:22
 * @description 授权端点
 **/
@RestController
@RequestMapping("api/authorize/v1")
class AuthorizeController(private val authorizeService: AuthorizeService) {
    /**
     * 进行认证 ....
     */
    @PostMapping
    fun authorize(param: Param): AppResult<AuthorizeVo> {
        authorizeService.authorize(param).let {
            AppResult.success(it)
        }
    }

}