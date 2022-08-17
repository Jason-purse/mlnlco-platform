package club.smileboy.mlnlco.service.util

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * @author JASONJ
 * @date 2022/8/16
 * @time 20:47
 * @description 为Page 提供一些方便的函数
 *
 *
 * 防止暴露更多的内部信息给外部 ...
 **/
class PageUtil {

    companion object {
        /**
         * copy
         */
        fun <T> Pageable.copy(): PageRequest {
            return PageRequest.of(pageNumber, pageSize)
        }

        /**
         * with sort
         */
        fun Pageable.withSort(sort: Sort): PageRequest {
            return PageRequest.of(pageNumber, pageSize).withSort(sort);
        }

        fun <T> Page<T>.copy(): AppPage<T> {
            return AppPage.copy(this)
        }
    }
}

/**
 * 好像jpa 是以 0作为page Number 的起点
 */
class AppPage<T>(
    private val pageNumber: Int = 1,
    private val pageSize: Int = 10,
    private val content: List<T> = emptyList(),
    private val totalSize: Long = 0,
    private val totalPage: Int = 0
) {
    companion object {
        /**
         * empty
         */
        fun empty(pageNumber: Int, pageSize: Int): AppPage<*> {
            return AppPage(pageNumber, pageSize, emptyList<Any>())
        }

        fun <T> copy(page: Page<T>): AppPage<T> {
            return AppPage(
                page.pageable.pageNumber,
                page.pageable.pageSize,
                page.content,
                page.totalElements,
                page.totalPages
            )
        }
    }
}