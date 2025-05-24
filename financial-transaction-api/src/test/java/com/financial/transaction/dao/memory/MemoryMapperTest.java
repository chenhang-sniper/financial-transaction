package com.financial.transaction.dao.memory;

import com.financial.transaction.dao.pojo.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MemoryMapperTest {

    @Test
    void testSave() {
        // Setup
        final MemoryMapper<MemoryEntity> memoryMapperUnderTest = new MemoryMapper<>();
        final MemoryEntity model = new MemoryEntity();
        model.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        model.setId(0L);

        // Run the test
        final MemoryEntity result = memoryMapperUnderTest.save(model);

        // Verify the results
    }

    @Test
    void testDeleteById() {
        // Setup
        final MemoryMapper<MemoryEntity> memoryMapperUnderTest = new MemoryMapper<>();

        // Run the test
        final boolean result = memoryMapperUnderTest.deleteById(0L);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testFindById() {
        // Setup
        final MemoryMapper<MemoryEntity> memoryMapperUnderTest = new MemoryMapper<>();

        // Run the test
        final MemoryEntity result = memoryMapperUnderTest.findById(0L);

        // Verify the results
    }

    @Test
    void testUpdateById() {
        // Setup
        final MemoryMapper<MemoryEntity> memoryMapperUnderTest = new MemoryMapper<>();
        final MemoryEntity model = new MemoryEntity();
        model.setTimestamp(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        model.setId(0L);

        // Run the test
        final MemoryEntity result = memoryMapperUnderTest.updateById(model);

        // Verify the results
    }

    @Test
    void testQueryList() {
        // Setup
        final MemoryMapper<MemoryEntity> memoryMapperUnderTest = new MemoryMapper<>();

        // Run the test
        final List<Transaction> result = memoryMapperUnderTest.queryList();

        // Verify the results
    }
}
