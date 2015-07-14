/*
 *
 *  * SBIF Client: Java/Groovy API client implementation for
 *    Superintendencia de Bancos e Instituciones Financieras de Chile
 *  * Copyright (C) 2015  Jose P. Bovet Derpich (jose.bovet@gmail.com)
 *  *
 *  * This program is free software; you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation; either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 */

package cl.kleedy.sbif

/**
 * Created by josebovet on 7/14/15.
 */
class TMCClientSpec extends SBIFClientSpec {


    void "should retrieve TMC list from 2015"() {
        when:
        def tmcs = client.getTMCByYear(2015)

        then:
        tmcs.size() > 0
    }

    void "should retrieve TMC list from 2015 and April"() {
        when:
        //TODO handle 404 error code
        def tmcs = client.getTMCByYearAndMonth(2014, 4)

        then:
        tmcs.size() > 0
    }

    void "should retrieve TMC list previous from 2015 and April"() {
        when:
        //TODO handle 404 error code
        def tmcs = client.getTMCByPreviousYearAndMonth(2014, 4)

        then:
        tmcs.size() > 0
    }

    void "should retrieve TMC list later than 2015 and April"() {
        when:
        //TODO handle 404 error code
        def tmcs = client.getTMCByLaterYearAndMonth(2014, 4)

        then:
        tmcs.size() > 0
    }

    void "should retrieve TMC list by period year 1-2010 April 5-2015"() {
        when:
        //TODO handle 404 error code
        def tmcs = client.getTMCByPeriodYearAndMonth(2010, 1, 2015, 5)
        //client.getDo

        then:
        tmcs.size() > 0
    }
}
