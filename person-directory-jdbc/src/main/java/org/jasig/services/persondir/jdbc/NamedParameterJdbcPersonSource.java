package org.jasig.services.persondir.jdbc;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasig.services.persondir.PersonAttributes;
import org.jasig.services.persondir.spi.SimpleSearchableAttributeSource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Eric Dalquist
 */
public class NamedParameterJdbcPersonSource implements SimpleSearchableAttributeSource {
    private NamedParameterJdbcOperations jdbcOperations;
    private String queryTemplate;
    private ResultSetExtractor<List<PersonAttributes>> resultSetExtractor;
    
    public void setJdbcOperations(JdbcOperations jdbcOperations) {
        this.jdbcOperations = new NamedParameterJdbcTemplate(jdbcOperations);
    }

    public void setNamedParameterJdbcOperations(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void setQueryTemplate(String queryTemplate) {
        this.queryTemplate = queryTemplate;
    }
    
    public void setResultSetExtractor(
            ResultSetExtractor<List<PersonAttributes>> resultSetExtractor) {
        this.resultSetExtractor = resultSetExtractor;
    }

    @Override
    public Set<String> getAvailableAttributes() {
        return Collections.emptySet();
    }
    
    @Override
    public List<PersonAttributes> searchForAttributes(Map<String, Object> searchAttributes) {
        return this.jdbcOperations.query(this.queryTemplate, searchAttributes, this.resultSetExtractor);
    }
}