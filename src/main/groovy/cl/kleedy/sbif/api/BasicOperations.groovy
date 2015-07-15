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

import wslite.http.HTTPClientException
import wslite.rest.RESTClient
import wslite.rest.Response

/**
 *
 * Created by josebovet on 7/14/15.
 */
class BasicOperations {

    String apiKey

    RESTClient restClient

    BasicOperations(RESTClient restClient, String apiKey) {
        this.restClient = restClient
        this.apiKey = apiKey
    }

    /***
     *  Obtiene un recurso segun parametros, path, recurso y nombre del metodo.
     * @param params
     * @param path
     * @param resource
     * @param methodName
     * @return
     */
    def List getResource(Map params = [:], String path, String resource, String methodName) {
        String url = buildURL(params, "/${path}", "${resource}")
        return "$methodName"(url)
    }

    /***
     * Obtiene un recurso segun parametros, path y nombre del metodo.
     * @param params
     * @param path
     * @param methodName
     * @return
     */
    def List getResource(Map params = [:], String path, String methodName) {
        return getResource(params, path, new String(), methodName)
    }

    /****
     * Construye una url request segun parametros y path
     * @param params
     * @param path ruta
     * @return url al recurso api
     */
    def String buildURL(Map params, String path) {
        return buildURL(params, path, new String(""))
    }

    /***
     *
     * @param params
     * @param path
     * @param resource
     * @return
     */
    def String buildURL(Map params, String path, String resource) {
        return path + resource + buildDate(params) + "?apikey=${apiKey}"
    }

    /***
     * Construye una url segun parametros.
     * @param params
     * @param includeDay :true
     * @return url
     */
    private String buildDate(Map params = [:], boolean includeDay = true) {
        def url = ''
        def day = '', month = '', year = '', year2 = '', month2 = '', period = ''
        def instance = Calendar.getInstance();

        if (params.size() == 0)
            return url

        if (params.day) {
            if (includeDay) {
                day = "/dias/${params.day}"
            }
            day << "/${params.day}"
        }

        if (params.month)
            month = "/${params.month}"
        else if (day)
            month = "/${instance.get(Calendar.MONTH) + 1}"

        if (params.year)
            year = "/${params.year}"
        else if (month)
            year = "/${instance.get(Calendar.YEAR)}"


        if (params.year2) {
            year2 = "/${params.year2}"
            if (params.month2) {
                month2 = "/${params.month2}"
                return year << month << year2 << month2
            }
        }


        return year << year2 << month << month2 << day
    }

    /***
     * Metodo para la llamada a la api
     * @param path
     * @query parameters
     * @return response
     */
    def Response call(path, Map query = [:]) throws SBIFClientException {
        def response
        try {
            query << ['formato': 'json']
            response = restClient.get(path: path, query: query)
        } catch (HTTPClientException hce) {
            throw new SBIFClientException("Problems communicating with: $path", hce)
        }
        return response
    }
}
