package io.github.ailtonbsj.multipledb.configs;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.dialect.PostgreSQLDialect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import io.github.ailtonbsj.multipledb.repositories.postgres.AuditLogRepository;

@Configuration
/*
@MapperScan(basePackages = "br.gov.ce.seduc.diarias",
        sqlSessionFactoryRef = "secondarySqlSessionFactory",
        annotationClass = SecondaryMapper.class)
*/
@MapperScan(basePackages = "io.github.ailtonbsj.multipledb.daos.postgres",
    sqlSessionFactoryRef = "postgresSqlSessionFactory")
@EnableJpaRepositories(
    basePackageClasses = AuditLogRepository.class,
    entityManagerFactoryRef = "postgresEntityManager")
public class PostgresDBConfig {

    @Bean
    @ConfigurationProperties(prefix = "auth.datasource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager(
        EntityManagerFactoryBuilder builder,
        @Qualifier("postgresDataSource") DataSource dataSource){

            Map<String, String> properties = new HashMap<>();
            properties.put("hibernate.dialect", PostgreSQLDialect.class.getName());
            properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
            properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
            properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");

            return builder.dataSource(dataSource).properties(properties)
                .packages("io.github.ailtonbsj.multipledb.models.postgres")
                .build();
    }

    @Bean
    SqlSessionFactory postgresSqlSessionFactory(@Qualifier("postgresDataSource") DataSource dataSource) throws Exception {
        var bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/myBatisPostgres/*.xml"));
        bean.setTypeAliasesPackage("io.github.ailtonbsj.multipledb.daos.postgres");
        return bean.getObject();
    }

}
