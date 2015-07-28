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

import cl.kleedy.sbif.api.indicadores.TMC

/**
 * Operaciones Tasa Maxima Convencional
 * Created by josebovet on 7/14/15.
 */
interface TMCOperations {

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC para el año solicitado
     * @param year
     * @return List < TMC >
     */
    def List<TMC> getTMCByYear(int year) throws SBIFClientException

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC para el año  y mes solicitado
     * @param year
     * @param month
     * @return List < TMC >
     */
    def List<TMC> getTMCByYearAndMonth(int year, int month) throws SBIFClientException

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC anteriores para el año  y mes solicitado
     * @param year
     * @param month
     * @return List < TMC >
     */
    def List<TMC> getTMCByPreviousYearAndMonth(int year, int month) throws SBIFClientException

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC posteriores para el año  y mes solicitado
     * @param year
     * @param month
     * @return List < TMC >
     */
    def List<TMC> getTMCByLaterYearAndMonth(int year, int month) throws SBIFClientException

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC periodo  para los años y meses solicitados
     * @param year
     * @param month
     * @return List < TMC >
     */
    def List<TMC> getTMCByPeriodYearAndMonth(int year, int month, int year2, int month2) throws SBIFClientException
}
