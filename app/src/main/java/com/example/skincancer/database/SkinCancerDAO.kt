package com.example.skincancer.database

import com.example.skincancer.model.SkinCancer
import com.example.skincancer.viewmodel.Ocl
import org.json.JSONObject
import java.lang.Exception
import org.json.JSONArray
import kotlin.collections.ArrayList

class SkinCancerDAO {

    companion object {

        fun getURL(command: String?, pars: ArrayList<String>, values: ArrayList<String>): String {
            var res = "base url for the data source"
            if (command != null) {
                res += command
            }
            if (pars.size == 0) {
                return res
            }
            res = "$res?"
            for (item in pars.indices) {
                val par = pars[item]
                val vals = values[item]
                res = "$res$par=$vals"
                if (item < pars.size - 1) {
                    res = "$res&"
                }
            }
            return res
        }

        fun isCached(id: String?): Boolean {
            val x: SkinCancer = SkinCancer.SkinCancerIndex.get(id) ?: return false
            return true
        }

        fun getCachedInstance(id: String): SkinCancer? {
            return SkinCancer.SkinCancerIndex.get(id)
        }

      fun parseCSV(line: String?): SkinCancer? {
          if (line == null) {
              return null
          }
          val line1vals: ArrayList<String> = Ocl.tokeniseCSV(line)
          var skinCancerx: SkinCancer? = SkinCancer.SkinCancerIndex.get(line1vals[0])
          if (skinCancerx == null) {
              skinCancerx = SkinCancer.createByPKSkinCancer(line1vals[0])
          }
          skinCancerx.id = line1vals[0].toString()
          skinCancerx.dates = line1vals[1].toString()
          skinCancerx.images = line1vals[2].toString()
          skinCancerx.outcome = line1vals[3].toString()
          return skinCancerx
      }


        fun parseJSON(obj: JSONObject?): SkinCancer? {
            return if (obj == null) {
                null
            } else try {
                val id = obj.getString("id")
                var skinCancerx: SkinCancer? = SkinCancer.SkinCancerIndex.get(id)
                if (skinCancerx == null) {
                    skinCancerx = SkinCancer.createByPKSkinCancer(id)
                }
                skinCancerx.id = obj.getString("id")
                skinCancerx.dates = obj.getString("dates")
                skinCancerx.images = obj.getString("images")
                skinCancerx.outcome = obj.getString("outcome")
                skinCancerx
            } catch (_e: Exception) {
                null
            }
        }

      fun makeFromCSV(lines: String?): ArrayList<SkinCancer> {
          val result: ArrayList<SkinCancer> = ArrayList<SkinCancer>()
          if (lines == null) {
              return result
          }
          val rows: ArrayList<String> = Ocl.parseCSVtable(lines)
          for (item in rows.indices) {
              val row = rows[item]
              if (row == null || row.trim { it <= ' ' }.length == 0) {
                  //trim
              } else {
                  val x: SkinCancer? = parseCSV(row)
                  if (x != null) {
                      result.add(x)
                  }
              }
          }
          return result
      }


        fun parseJSONArray(jarray: JSONArray?): ArrayList<SkinCancer>? {
            if (jarray == null) {
                return null
            }
            val res: ArrayList<SkinCancer> = ArrayList<SkinCancer>()
            val len = jarray.length()
            for (i in 0 until len) {
                try {
                    val x = jarray.getJSONObject(i)
                    if (x != null) {
                        val y: SkinCancer? = parseJSON(x)
                        if (y != null) {
                            res.add(y)
                        }
                    }
                } catch (e: Exception) {
                     e.printStackTrace()
                }
            }
            return res
        }


        fun writeJSON(_x: SkinCancer): JSONObject? {
            val result = JSONObject()
            try {
                result.put("id", _x.id)
                result.put("dates", _x.dates)
                result.put("images", _x.images)
                result.put("outcome", _x.outcome)
            } catch (_e: Exception) {
                return null
            }
            return result
        }


        fun parseRaw(obj: Any?): SkinCancer? {
             if (obj == null) {
                 return null
            }
            try {
                val _map = obj as HashMap<String, Object>
                val id: String = _map["id"].toString()
                var _skinCancerx: SkinCancer? = SkinCancer.SkinCancerIndex.get(id)
                if (_skinCancerx == null) {
                    _skinCancerx = SkinCancer.createByPKSkinCancer(id)
                }
                _skinCancerx.id = _map["id"].toString()
                _skinCancerx.dates = _map["dates"].toString()
                _skinCancerx.images = _map["images"].toString()
                _skinCancerx.outcome = _map["outcome"].toString()
                return _skinCancerx
            } catch (_e: Exception) {
                return null
            }
        }

        fun writeJSONArray(es: ArrayList<SkinCancer>): JSONArray {
            val result = JSONArray()
            for (_i in 0 until es.size) {
                val _ex: SkinCancer = es[_i]
                val _jx = writeJSON(_ex)
                if (_jx == null) {
                    //null
                } else {
                    try {
                        result.put(_jx)
                    } catch (_ee: Exception) {
                    }
                }
            }
            return result
        }
    }
}
