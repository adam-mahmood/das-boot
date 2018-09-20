package com.boot.das_boot.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.das_boot.model.Shipwreck;
import com.boot.das_boot.repository.ShipwreckRepository;

public class ShipWreckControllerTest {

	@InjectMocks
	private ShipwreckController sc;

	@Mock
	private ShipwreckRepository shipwreckRepository;

	@Before
	public void init() {
		// when this is passed in it will look at @InjectMocks and @Mocks attributes,
		// and it will see if they need to be pushed togther. In this case Mockito would
		// set shipwreckRepository inside ShipController much like Spring Dependncy
		// Injection
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testShipWreckTest() {
		Shipwreck sw = new Shipwreck();
		sw.setId(1L);
		when(shipwreckRepository.findById(1L)).thenReturn(Optional.of(sw));

		Shipwreck wreck = sc.get(1L);
		verify(shipwreckRepository).findById(1L);
		assertThat(wreck, is(notNullValue()));
		assertThat(1L, is(wreck.getId()));
	}
}
