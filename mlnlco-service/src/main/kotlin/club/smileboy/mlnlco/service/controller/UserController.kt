package club.smileboy.mlnlco.service.controller

import club.smileboy.mlnlco.service.model.params.*
import club.smileboy.mlnlco.service.model.params.annotations.QueryType
import club.smileboy.mlnlco.service.service.UserService
import club.smileboy.mlnlco.service.util.AppResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * @author FLJ
 * @date 2022/8/17
 * @time 14:05
 * @Description 用户controller
 */
@RestController
@RequestMapping("api/user/v1")
class UserController(private val userService: UserService) {

    /**
     * 查询所有的用户且分页
     */
    @GetMapping
    @QueryType("app_user", targetType = UserQuery::class)
    fun findAllByPage(query: Query, pageable: Pageable): AppResult<Page<AppUserVo>> {
        return userService.findAllByPageAndQuery(query, pageable).let {
            AppResult.success(it.orElse(Page.empty(pageable)))
        }
    }

    /**
     * 保存一个用户信息
     */
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @QueryType("app_user", targetType = UserSaveParam::class, fromBody = true)
    fun saveUserBy(param: Param): AppResult<*> {
        return userService.saveOneByParam(param).let {
            AppResult.success()
        }
    }

    /**
     * 更新一个用户信息
     */
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @QueryType("app_user", targetType = UserUpdateParam::class, fromBody = true)
    fun updateUserBy(param: Param): AppResult<*> {
        return userService.updateOneByParam(param).let {
            AppResult.success()
        }
    }

    /**
     * 删除一个用户信息
     */
    @DeleteMapping("{id:\\d+}")
    fun deleteUserById(@PathVariable("id") id: Int): AppResult<*> {
        return userService.deleteOneById(id).let {
            AppResult.success()
        }
    }

}