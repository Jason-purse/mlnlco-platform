package club.smileboy.mlnlco.service.service

import club.smileboy.mlnlco.service.model.params.Param
import club.smileboy.mlnlco.service.model.params.Query
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:16
 * @Description 基础服务抽象
 */
interface BaseService<T> {
    /**
     * 根据查询条件和 分页条件查询 所有 T 信息
     */
    fun findAllByPageAndQuery(query: Query, pageable: Pageable): Optional<Page<T>>

    /**
     * 查询所有的 T 信息
     */
    fun findAllByQuery(query: Query): Optional<List<T>>


    /**
     * 删除一个 T 信息
     */
    fun deleteOneByQuery(param: Param)

    /**
     * 删除 List<T> 信息
     */
    fun deleteListByQuery(param: Param)

    /**
     * 更新一个 T 信息
     */
    fun updateOneByQuery(param: Param)

    /**
     * 更新一堆T 信息
     */
    fun updateListByQuery(param: Param)
}