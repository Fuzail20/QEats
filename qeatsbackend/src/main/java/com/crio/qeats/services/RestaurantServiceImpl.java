
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;



  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
        
        int currentHour = currentTime.getHour();

        // Define peak hours
        int peakStart1 = 8;
        int peakEnd1 = 10;

        int peakStart2 = 13;
        int peakEnd2 = 14;

        int peakStart3 = 19;
        int peakEnd3 = 21;

        // Define service radii
        int peakServiceRadius = 3;  // 3KMs during peak hours
        int offPeakServiceRadius = 5;  // 5KMs during non-peak hours

        Double servingRadius;
        // Check conditions based on the current hour
        if ((currentHour >= peakStart1 && currentHour <= peakEnd1)
            || (currentHour >= peakStart2 && currentHour <= peakEnd2)
            || (currentHour >= peakStart3 && currentHour <= peakEnd3)) {
                servingRadius = peakHoursServingRadiusInKms;
            } else {
            servingRadius = normalHoursServingRadiusInKms;
        }

        List<Restaurant> restaurantList = restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), currentTime, servingRadius);
        return GetRestaurantsResponse.builder().restaurants(restaurantList).build();
  }
  
}

