package com.gapatmej.predictor.web.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gapatmej.predictor.PredictorApplication;
import com.gapatmej.predictor.service.dto.RestrictionDTO;

/**
 * Test class for the RestrictionResource REST controller.
 *
 * @see RestrictionResource
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PredictorApplication.class)
@WebAppConfiguration
public class RestrictionResourceTest extends AbstractTest  {


  @BeforeEach
  protected void setUp() {
    super.setUp();
  }

  @Test
  public void getRestrictions() throws Exception {
    String uri = "/api/restriction/HCK-071/22062020/12/30";
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    RestrictionDTO[] restrictions = mapFromJson(content, RestrictionDTO[].class);
    assertTrue(restrictions.length > 0);
  }

}
