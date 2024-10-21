package io.github.ailtonbsj.multipledb.configs;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.community.dialect.OracleLegacyDialect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import io.github.ailtonbsj.multipledb.repositories.oracle.ActiveSessionRepository;

@Configuration
@MapperScan(basePackages = "io.github.ailtonbsj.multipledb.daos.oracle",
    sqlSessionFactoryRef = "oracleSqlSessionFactory")
@EnableJpaRepositories(
    basePackageClasses = ActiveSessionRepository.class,
    entityManagerFactoryRef = "oracleEntityManager")
public class OracleDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean oracleEntityManager(
        EntityManagerFactoryBuilder builder,
        @Qualifier("oracleDataSource") DataSource dataSource){
            
            Map<String, String> properties = new HashMap<>();
            properties.put("hibernate.dialect", OracleLegacyDialect.class.getName());
            properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
            properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
            properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");

            return builder.dataSource(dataSource).properties(properties)
                .packages("io.github.ailtonbsj.multipledb.models.oracle")
                .build();
    }

    @Primary
    @Bean
    SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        var bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/myBatisOracle/*.xml"));
        bean.setTypeAliasesPackage("io.github.ailtonbsj.multipledb.daos.oracle");
        return bean.getObject();
    }

}
