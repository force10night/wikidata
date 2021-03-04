package scibite_tech_test.wikidata.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestfulRuntimeExceptionTest {

	@Test
	void testRestfulRuntimeExceptionMessage() {
		Throwable exception = assertThrows(
	            RestfulRuntimeException.class, () -> {
	            	testExceptionClass user = new testExceptionClass(null);
	            }
	    );
	 
	    assertEquals("test cannot be null", exception.getMessage());
	}

	

	@Test
	void testRestfulRuntimeExceptionMessageCause() {
		Throwable exception = assertThrows(
	            RestfulRuntimeException.class, () -> {
	            	testExceptionClass user = new testExceptionClass("message and cause");
	            }
	    );
		assertEquals("test with message exception", exception.getMessage());
	    assertEquals("test was a throwable cause", exception.getCause().getMessage());
	}

	@Test
	void testRestfulRuntimeExceptionCause() {
		Throwable exception = assertThrows(
	            RestfulRuntimeException.class, () -> {
	            	testExceptionClass user = new testExceptionClass("cause");
	            }
	    );
	 
	    assertEquals("test was a throwable cause", exception.getCause().getMessage());
	}

	@Test
	void testRestfulRuntimeExceptionMessageCauseTrueTrue() {
		Throwable exception = assertThrows(
	            RestfulRuntimeException.class, () -> {
	            	testExceptionClass user = new testExceptionClass("No suppression and no Stacktrace");
	            }
	    );
	 
		assertEquals("test with message exception", exception.getMessage());
	    assertEquals("test was a throwable cause", exception.getCause().getMessage());
	    assertNotNull(exception.getStackTrace());
	    assertNotNull(exception.getSuppressed());
	}
	
	
	private class testExceptionClass {
		private String test;

		public testExceptionClass(String test) throws RestfulRuntimeException {
			this.test = test;
			if(this.test == null) {
				throw new RestfulRuntimeException("test cannot be null");
			}
			else if("cause".equals(test)) {
				throw new RestfulRuntimeException(new Throwable("test was a throwable cause"));
			}
			else if("message and cause".equals(test)) {
				throw new RestfulRuntimeException("test with message exception",new Throwable("test was a throwable cause"));
			}
			else if("No suppression and no Stacktrace".equals(test)) {
				throw new RestfulRuntimeException("test with message exception",new Throwable("test was a throwable cause"),true,true);
			}
		}
		
	}
}
