package club.smileboy.mlnlco.service.model.params;

import org.springframework.data.jpa.domain.Specification;

public interface Query extends Operation {


    /**
     * 将参数转换为 Specification<?>
     * @return 查询规范
     */
    Specification<?> convert();
}
