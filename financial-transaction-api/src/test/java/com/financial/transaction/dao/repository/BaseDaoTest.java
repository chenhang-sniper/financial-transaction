package com.financial.transaction.dao.repository;

import com.financial.transaction.dao.memory.MemoryEntity;
import com.financial.transaction.dao.memory.MemoryMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BaseDaoTest {

    @Mock
    private MemoryMapper mockMemoryMapper;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testQueryById() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;

        // Configure MemoryMapper.findById(...).
        final MemoryEntity memoryEntity = new MemoryEntity();
        memoryEntity.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        memoryEntity.setId(0L);
        when(mockMemoryMapper.findById(0L)).thenReturn(memoryEntity);

        // Run the test
        final MemoryEntity result = baseDaoUnderTest.queryById("value");

        // Verify the results
    }

    @Test
    void testQueryById_MemoryMapperReturnsNull() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        when(mockMemoryMapper.findById(0L)).thenReturn(null);

        // Run the test
        final MemoryEntity result = baseDaoUnderTest.queryById("value");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testExistsById() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;

        // Configure MemoryMapper.findById(...).
        final MemoryEntity memoryEntity = new MemoryEntity();
        memoryEntity.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        memoryEntity.setId(0L);
        when(mockMemoryMapper.findById(0L)).thenReturn(memoryEntity);

        // Run the test
        final boolean result = baseDaoUnderTest.existsById("value");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testExistsById_MemoryMapperReturnsNull() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        when(mockMemoryMapper.findById(0L)).thenReturn(null);

        // Run the test
        final boolean result = baseDaoUnderTest.existsById("value");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testQueryOptionalById() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;

        // Configure MemoryMapper.findById(...).
        final MemoryEntity memoryEntity = new MemoryEntity();
        memoryEntity.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        memoryEntity.setId(0L);
        when(mockMemoryMapper.findById(0L)).thenReturn(memoryEntity);

        // Run the test
        final Optional<MemoryEntity> result = baseDaoUnderTest.queryOptionalById("value");

        // Verify the results
    }

    @Test
    void testQueryOptionalById_MemoryMapperReturnsNull() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        when(mockMemoryMapper.findById(0L)).thenReturn(null);

        // Run the test
        final Optional<MemoryEntity> result = baseDaoUnderTest.queryOptionalById("value");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testInsert() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        final MemoryEntity model = new MemoryEntity();
        model.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        model.setId(0L);

        // Configure MemoryMapper.save(...).
        final MemoryEntity memoryEntity = new MemoryEntity();
        memoryEntity.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        memoryEntity.setId(0L);
        when(mockMemoryMapper.save(any(MemoryEntity.class))).thenReturn(memoryEntity);

        // Run the test
        final boolean result = baseDaoUnderTest.insert(model);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testInsert_MemoryMapperReturnsNull() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        final MemoryEntity model = new MemoryEntity();
        model.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        model.setId(0L);

        when(mockMemoryMapper.save(any(MemoryEntity.class))).thenReturn(null);

        // Run the test
        final boolean result = baseDaoUnderTest.insert(model);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testUpdateById() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        final MemoryEntity model = new MemoryEntity();
        model.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        model.setId(0L);

        // Configure MemoryMapper.updateById(...).
        final MemoryEntity memoryEntity = new MemoryEntity();
        memoryEntity.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        memoryEntity.setId(0L);
        when(mockMemoryMapper.updateById(any(MemoryEntity.class))).thenReturn(memoryEntity);

        // Run the test
        final boolean result = baseDaoUnderTest.updateById(model);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testUpdateById_MemoryMapperReturnsNull() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        final MemoryEntity model = new MemoryEntity();
        model.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        model.setId(0L);

        when(mockMemoryMapper.updateById(any(MemoryEntity.class))).thenReturn(null);

        // Run the test
        final boolean result = baseDaoUnderTest.updateById(model);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testDeleteById() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        when(mockMemoryMapper.deleteById(0L)).thenReturn(false);

        // Run the test
        final boolean result = baseDaoUnderTest.deleteById("value");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testDeleteById_MemoryMapperReturnsTrue() {
        // Setup
        final BaseDao<MemoryEntity, MemoryMapper<MemoryEntity>> baseDaoUnderTest = new BaseDao<>() {};
        baseDaoUnderTest.memoryMapper = mockMemoryMapper;
        when(mockMemoryMapper.deleteById(0L)).thenReturn(true);

        // Run the test
        final boolean result = baseDaoUnderTest.deleteById("value");

        // Verify the results
        assertTrue(result);
    }
}
