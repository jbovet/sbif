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

package cl.kleedy.sbif.api

import cl.kleedy.sbif.api.indicadores.Dolar

/**
 * Operaciones con USD dolar.
 * Created by josebovet on 7/14/15.
 */
interface DolarOperations {

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del año que se indique.
     * @param year
     * @return List<Dolar>
     */
    def List<Dolar> getDolares(int year)

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para el actual año
     * @param year
     * @return List<Dolar>
     */
    def List<Dolar> getDolares()

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del mes del año que se indique.
     * @param year
     * @param month
     * @return List<Dolar>
     */
    def List<Dolar> getDolares(int year, int month)

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para dia mes y año que se indique.
     * @param year
     * @param month
     * @param day
     * @return List<Dolar>
     */
    def Dolar getDolares(int year, int month, int day)

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para los años siguientes al año que se indique.
     * @return List<Dolar>
     */
    def List<Dolar> getDolaresPosteriores(int year)

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del mes del año que se indique.
     * @param year
     * @param month
     * @return List<Dolar>
     */
    def List<Dolar> getDolaresPosteriores(int year, int month)

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para los años anteriores al año que se indique.
     * @return List<Dolar>
     */
    def List<Dolar> getDolaresAnteriores(int year)

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para mes y año que se indique.
     * @param year
     * @param month
     * @return List <Dolar>
     */
    def List<Dolar> getDolaresAnteriores(int year, int month)

    /***
     * Permite obtener un listado del Dólar EE.UU. para cada uno de los días
     * incluidos dentro de los años que se indiquen en los parámetros.
     * @param year
     * @param year2
     * @return List<Dolar>
     */
    def List<Dolar> getDolaresPeriodo(int year, int year2)

    /***
     * Permite obtener un el valor del Dólar EE.UU. para el día actual.
     * @return Dolar
     */
    def Dolar getDolar()
}

