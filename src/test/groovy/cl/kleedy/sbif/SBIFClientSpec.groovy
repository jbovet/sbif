/*
 * SBIF Client: Java/Groovy API client implementation for
   Superintendencia de Bancos e Instituciones Financieras de Chile
 * Copyright (C) 2015  Jose P. Bovet Derpich (jose.bovet@gmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cl.kleedy.sbif

import spock.lang.Specification

/**
 * Created by josebovet on 7/1/15.
 */
class SBIFClientSpec extends Specification {

    SBIFClient client;

    def apiKey = "NEW_API_KEY"

    void setup() {
        client = SBIFClient.instance(apiKey)
    }


    void "should retrieve date and dolar price"() {
        when:
        def dolar = client.getDolar()

        then:
        dolar.fecha != null
        dolar.valor >= 600
    }

    void "should retrieve dolar list from 2014"() {
        when:
        def dolars = client.getDolares(2014)

        then:
        dolars.size() > 0

    }

    void "should retrieve dolar list from this year"() {
        when:
        def dolars = client.getDolares()

        then:
        dolars.size() > 0

    }

    void "should retrieve dolar for 2 julio 2015"() {
        when:
        def dolar = client.getDolares(2015, 7, 2)

        then:
        dolar.fecha != null
        dolar.valor == 639.04

    }


    void "should retrieve dolar list from year later than year 2014"() {
        when:
        def dolares = client.getDolaresPosteriores(2014)

        then:
        dolares.size() > 0

    }

}
