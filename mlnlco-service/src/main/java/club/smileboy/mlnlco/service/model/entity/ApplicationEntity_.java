package club.smileboy.mlnlco.service.model.entity;

import club.smileboy.mlnlco.service.model.propertyEnum.AppSecret;
import club.smileboy.mlnlco.service.model.propertyEnum.AppSecretType;
import club.smileboy.mlnlco.service.model.propertyEnum.ApplicationType;
import club.smileboy.mlnlco.service.model.propertyEnum.IcoType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ApplicationEntity.class)
public abstract class ApplicationEntity_ extends club.smileboy.mlnlco.service.model.entity.BaseEntity_ {

	public static volatile SingularAttribute<ApplicationEntity, String> sub;
	public static volatile SingularAttribute<ApplicationEntity, AppSecretType> appSecretType;
	public static volatile SingularAttribute<ApplicationEntity, String> ico;
	public static volatile SingularAttribute<ApplicationEntity, ApplicationType> appType;
	public static volatile SingularAttribute<ApplicationEntity, String> appName;
	public static volatile SingularAttribute<ApplicationEntity, String> appId;
	public static volatile SingularAttribute<ApplicationEntity, String> id;
	public static volatile SingularAttribute<ApplicationEntity, IcoType> icoType;
	public static volatile SingularAttribute<ApplicationEntity, AppSecret> appSecret;

	public static final String SUB = "sub";
	public static final String APP_SECRET_TYPE = "appSecretType";
	public static final String ICO = "ico";
	public static final String APP_TYPE = "appType";
	public static final String APP_NAME = "appName";
	public static final String APP_ID = "appId";
	public static final String ID = "id";
	public static final String ICO_TYPE = "icoType";
	public static final String APP_SECRET = "appSecret";

}

