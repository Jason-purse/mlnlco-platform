package club.smileboy.mlnlco.service.model.params;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

public interface Query extends Operation {


    /**
     * 将参数转换为 Specification<?>
     * @return 查询规范
     */
    @NonNull
    Specification<?> toSpecification();



    /**
     * 是否需要转换为 Specification
     * @return true / false
     */
    boolean checkNeedToSpecification();
}
