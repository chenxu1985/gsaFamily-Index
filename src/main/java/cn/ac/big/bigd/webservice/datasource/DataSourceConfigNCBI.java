package cn.ac.big.bigd.webservice.datasource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 声明配置类信息
 *
 * @Primary ：默认数据源
 */
@Configuration
/**扫描对应数据源下mapper路径*/
@MapperScan(basePackages = "cn.ac.big.bigd.webservice.mapper.ncbi", sqlSessionFactoryRef = "ncbiSqlSessionFactory")
public class DataSourceConfigNCBI {

    /**
     * 创建DataSource
     */
    @Bean(name = "ncbiDataSource")
    @Qualifier("ncbiDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ncbi")
    public DataSource getDateSource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    /**
     * DataSource注入sqlSessionFactory
     */
    @Bean(name = "ncbiSqlSessionFactory")
    public SqlSessionFactory ncbiSqlSessionFactory(@Qualifier("ncbiDataSource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //注入数据源
        bean.setDataSource(datasource);
        bean.setVfs(SpringBootVFS.class);
        //扫描对应model url
        bean.setTypeAliasesPackage("cn.ac.big.bigd.webservice.model.ncbi");
        //扫描对应mapper.xml路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/ncbi/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    /**
     * SqlSessionFactory注入SqlSessionTemplate方法
     */
    @Bean("ncbiSqlSessionTemplate")
    public SqlSessionTemplate ncbiSqlSessionTemplate(@Qualifier("ncbiSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }
}