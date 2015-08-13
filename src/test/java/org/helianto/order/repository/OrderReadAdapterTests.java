package org.helianto.order.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class OrderReadAdapterTests {

	@Test
	public void constructor() {
		OrderReadAdapter adapter = new OrderReadAdapter(
				1          // int id
				, 1000L    // Long internalNumber
				, 5        // Integer partId
				, "CODE"   // String docCode
				, "NAME"   // String docName
				, new DateTime(2001, 01, 01, 12, 30).toDate() // Date issueDate
				, 20       // Integer ownerId
				, "PERSON" //String ownerDisplayName
				, "IMAGE"  // String ownerImageUrl
				, 'T'      // Character resolution
				, new DateTime(2001, 01, 02, 12, 30).toDate() // Date nextCheckDate
				, new DateTime(2001, 01, 03, 12, 30).toDate() // Date checkOutTime
				, 30       // Integer categoryId
				, "CAT"    // String categoryCode
				, "CATEGORY"      // String categoryName
				, "TOKEN1;TOKEN2" // String checkInData
				, "TOKEN3 TOKEN4" // String checkOutData
				, "REMARKS"       // String remarks     
				, "TOKEN"        // String tokenPrefix
				, 40 // Integer currencyId
				, new BigDecimal(10) // BigDecimal faceValue
				, 'X' // Character position
				);
		assertEquals(1, (int) adapter.getId());
		assertEquals(1000L, (long) adapter.getInternalNumber());
		assertEquals(5, (int) adapter.getPartId());
		assertEquals("CODE", adapter.getDocCode());
		assertEquals("NAME", adapter.getDocName());
		assertEquals(new DateTime(2001, 01, 01, 12, 30).toDate(), adapter.getIssueDate());
		assertEquals(20, (int) adapter.getOwnerId());
		assertEquals("PERSON", adapter.getOwnerDisplayName());
		assertEquals("IMAGE", adapter.getOwnerImageUrl());
		assertEquals('T', (char) adapter.getResolution());
		assertEquals(new DateTime(2001, 01, 02, 12, 30).toDate(), adapter.getNextCheckDate());
		assertEquals(new DateTime(2001, 01, 03, 12, 30).toDate(), adapter.getCheckOutTime());
		assertEquals(30, adapter.getCategoryId().intValue());
		assertEquals("CAT", adapter.getCategoryCode());
		assertEquals("CATEGORY", adapter.getCategoryName());
		assertEquals("TOKEN1 TOKEN2".split(" "), adapter.getCheckInDataAsArray());
		assertEquals("REMARKS", adapter.getRemarks());
		assertEquals("TOKEN", adapter.getTokenPrefix());
		assertEquals(40, (int) adapter.getCurrencyId());
		assertEquals(new BigDecimal(10), adapter.getFaceValue());
		assertEquals('X', (char) adapter.getPosition());
	}

}
