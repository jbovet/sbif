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

import cl.kleedy.sbif.api.indicadores.Euro

/**
 *  Operaciones con EURO
 * Created by josebovet on 7/23/15.
 */
interface EuroOperations {

    /****
     * Permite obtener el valor del Euro para el día actual.
     * @return Euro
     */
    def Euro getEuro()

    /***
     * Permite obtener un listado con el valor del Euro para cada día del año que se indique.
     * @param year
     * @return List<Euro>
     */
    def List<Euro> getEuroByYear(int year);

    /***
     * Permite obtener un listado con el valor del Euro para cada día y mes del año que se indique.
     * @param year
     * @param month
     * @return List<Euro>
     */
    def List<Euro> getEuroByYearAndMonth(int year, int month);

    /***
     * Permite obtener el valor del Euro para una fecha específica.
     * @param year
     * @param month
     * @param day
     * @return Euro
     */
    def Euro getEuroByYearAndMonthAndDay(int year, int month, int day);

    /***
     * Permite obtener un listado con el valor del Euro para los años siguientes al año que se indique.
     * @param year
     * @return <Euro>
     */
    def List<Euro> getEuroLaterYear(int year);

    /***
     * Permite obtener un listado con el valor del Euro para los años y meses siguientes se indique.
     * @param year
     * @param month
     * @return
     */
    def List<Euro> getEuroLaterYearAndMonth(int year, int month);

    /***
     * Permite obtener un listado con el valor del Euro posteriores a la fecha que se indique.
     * @param year
     * @param month
     * @param day
     * @return
     */
    def List<Euro> getEuroLaterYearAndMonthAndDay(int year, int month, int day);

}

