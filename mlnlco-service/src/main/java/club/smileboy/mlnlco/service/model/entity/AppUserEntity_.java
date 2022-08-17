package club.smileboy.mlnlco.service.model.entity;

import club.smileboy.mlnlco.service.model.propertyEnum.SexType;
import club.smileboy.mlnlco.service.model.propertyEnum.UserOrigin;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUserEntity.class)
public abstract class AppUserEntity_ extends club.smileboy.mlnlco.service.model.entity.BaseEntity_ {

	public static volatile SingularAttribute<AppUserEntity, Date> birthday;
	public static volatile SingularAttribute<AppUserEntity, String> password;
	public static volatile SingularAttribute<AppUserEntity, String> phone;
	public static volatile SingularAttribute<AppUserEntity, SexType> sex;
	public static volatile SingularAttribute<AppUserEntity, String> nickname;
	public static volatile SingularAttribute<AppUserEntity, Integer> id;
	public static volatile SingularAttribute<AppUserEntity, String> email;
	public static volatile SingularAttribute<AppUserEntity, String> username;
	public static volatile SingularAttribute<AppUserEntity, UserOrigin> originType;

	public static final String BIRTHDAY = "birthday";
	public static final String PASSWORD = "password";
	public static final String PHONE = "phone";
	public static final String SEX = "sex";
	public static final String NICKNAME = "nickname";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";
	public static final String ORIGIN_TYPE = "originType";

}
