package lt.markmerkk

import org.junit.Assert.*
import org.junit.Test

/**
 * @author mariusmerkevicius
 * *
 * @since 2016-11-06
 */
class MusicAssemblerTest {
    @Test
    fun testInit() {
        // Arrange
        val assembler = MusicAssembler()

        // Act
        // Assert
        assertNotNull(assembler)
    }
}