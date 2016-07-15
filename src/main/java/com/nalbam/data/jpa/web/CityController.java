/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nalbam.data.jpa.web;

import com.nalbam.data.jpa.domain.City;
import com.nalbam.data.jpa.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET, value = "all")
    public Page<City> all() {
        return this.cityService.findAll();
    }

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET, value = "search")
    public Page<City> search(@RequestParam(value = "name", defaultValue = "") String name, Pageable pageable) {
        return this.cityService.findCities(name, pageable);
    }

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET, value = "")
    public City get(@RequestParam(value = "state", defaultValue = "Bath") String state,
                    @RequestParam(value = "country", defaultValue = "UK") String country) {
        return this.cityService.getCity(state, country);
    }

}
