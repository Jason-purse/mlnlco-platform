package club.smileboy.mlnlco.service.model.params;
/**
 * @author FLJ
 * @date 2022/8/15
 * @time 16:54
 * @Description 顶层操作抽象
 */
public interface Operation {
    /**
     * 获取需要操作的 信息类型
     * @return
     */
    public String getEntityType();
}
