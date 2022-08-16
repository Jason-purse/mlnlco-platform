package club.smileboy.mlnlco.service.model.propertyEnum;

/**
 * @author FLJ
 * @date 2022/8/15
 * @time 12:41
 * @Description 图标类型
 */
public enum IcoType implements PropertyEnum<IcoType> {
    BASE64 {
        @Override
        public boolean support(String icoStr) {
            // base64 data开头
            return icoStr.trim().startsWith("data:");
        }
    },
    URL {
        @Override
        public boolean support(String icoStr) {
            try {
                // 判断是否符合语法规则 ..
                new java.net.URL(icoStr);
                return true;
            } catch (Exception e) {
                // pass
            }
            return false;
        }
    },
    NONE;

    @Override
    public String asText() {
        return name();
    }

    public boolean support(String icoStr) {
        return false;
    }

    /**
     * 根据 icoStr 解析合适的 图标类型
     * @param icoStr 图标字符串
     * @return icoType
     * @exception IllegalArgumentException 如果无法解析为有效的图标类型,则抛出异常 ...
     */
    public static IcoType resolveIcoType(String icoStr) {
        for (IcoType value : IcoType.values()) {
            if (value.support(icoStr)) {
                return value;
            }
        }
        throw new IllegalArgumentException("can't find appropriate ico type !!!");
    }
}
