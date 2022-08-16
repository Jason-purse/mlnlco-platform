package club.smileboy.mlnlco.service.controller

import club.smileboy.mlnlco.service.model.params.*
import club.smileboy.mlnlco.service.model.params.annotations.QueryType
import club.smileboy.mlnlco.service.service.ApplicationService
import club.smileboy.mlnlco.service.util.AppResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/app/v1")
class ApplicationController(private val applicationService: ApplicationService) {

    /**
     * 分页查询所有的 app info
     */
    @GetMapping
    fun findAllByPage(
        @QueryType(value = "application", targetType = ApplicationQuery::class) query: Query,
        pageable: Pageable
    ): AppResult<Page<ApplicationVo>> {
        return AppResult.success(
            applicationService.findAllByPageAndQuery(query, pageable).orElseGet { Page.empty(pageable) })
    }

    /**
     * 新增一个应用详情
     */
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @QueryType("application", targetType = ApplicationSaveParam::class, fromBody = true)
    fun saveAppBy(param: Param): AppResult<*> {
        return applicationService.saveOneByParam(param).let {
            AppResult.success()
        }
    }

    /**
     * 更新一个应用详情
     */
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @QueryType("application", targetType = ApplicationUpdateParam::class,fromBody = true)
    fun updateAppBy(param: Param): AppResult<*> {
        return applicationService.updateOneByParam(param).let {
            AppResult.success()
        }
    }
}