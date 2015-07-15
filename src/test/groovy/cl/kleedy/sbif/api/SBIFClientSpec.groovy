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
package cl.kleedy.sbif.api

import spock.lang.Specification

/**
 * Created by josebovet on 7/1/15.
 */
class SBIFClientSpec extends Specification {

    SBIFTemplate sbifTemplate;

    def apiKey = "0dd3d3e332a0210d79eec9aabdd2abff5fe62de4"

    void setup() {
        sbifTemplate = new SBIFTemplate(apiKey);
    }

}
