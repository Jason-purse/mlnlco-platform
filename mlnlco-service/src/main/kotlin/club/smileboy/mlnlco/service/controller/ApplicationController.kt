package club.smileboy.mlnlco.service.controller

import club.smileboy.mlnlco.service.model.params.ApplicationQuery
import club.smileboy.mlnlco.service.model.params.Query
import club.smileboy.mlnlco.service.model.params.annotations.QueryType
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/app/v1")
class ApplicationController() {

    /**
     * 分页查询所有的 app info
     */
    @GetMapping
    fun findAllByPage(
        @QueryType(value = "application", targetType = ApplicationQuery::class) query: Query,
        pageable: Pageable
    ) {


    }
}