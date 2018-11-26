package com.wangtiansoft.KingDarts.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.wangtiansoft.KingDarts.persistence.dao.record", sqlSessionFactoryRef = "recordSqlSessionFactory", sqlSessionTemplateRef = "recordSqlSessionTemplate")
public class RecordDataSourceConfig {

	@Bean(name = "recordDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.record")
	public DataSource setDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	@Bean(name = "recordTransactionManager")
	public DataSourceTransactionManager setTransactionManager(@Qualifier("recordDataSource") DataSource dataSource) {
		return  new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "recordSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext, @Qualifier("recordDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mapper/record/*.xml"));

		//分页插件
       /* Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[] {interceptor});*/

		return sessionFactory.getObject();
	}

	@Bean(name = "recordSqlSessionTemplate")
	public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("recordSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
	
	/*@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		//数据库
		properties.setProperty("helperDialect", "mysql");
		//是否将参数offset作为PageNum使用
		properties.setProperty("offsetAsPageNum", "true");
		//是否进行count查询
		properties.setProperty("rowBoundsWithCount", "true");
		//是否分页合理化
		properties.setProperty("reasonable", "false");
		pageHelper.setProperties(properties);
		return pageHelper;
	}*/

}
