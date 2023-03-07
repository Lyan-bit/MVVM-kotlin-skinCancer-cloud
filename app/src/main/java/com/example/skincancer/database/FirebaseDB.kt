package com.example.skincancer.database

import com.example.skincancer.model.SkinCancer
import com.example.skincancer.SkinCancerVO
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class FirebaseDB() {

    var database: DatabaseReference? = null

    companion object {
        private var instance: FirebaseDB? = null
        fun getInstance(): FirebaseDB {
            return instance ?: FirebaseDB()
        }
    }

    init {
        connectByURL("https://patient-161e1-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    fun connectByURL(url: String) {
        database = FirebaseDatabase.getInstance(url).reference
        if (database == null) {
            return
        }
        val skinCancer_listener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get instances from the cloud database
                val _skinCancers = dataSnapshot.value as HashMap<String, Object>?
                if (_skinCancers != null) {
                    val _keys = _skinCancers.keys
                    for (_key in _keys) {
                        val _x = _skinCancers[_key]
                        SkinCancerDAO.parseRaw(_x)
                    }
                    // Delete local objects which are not in the cloud:
                    val _locals = ArrayList<SkinCancer>()
                    _locals.addAll(SkinCancer.SkinCancerAllInstances)
                    for (_x in _locals) {
                        if (_keys.contains(_x.id)) {
                        } else {
                            SkinCancer.killSkinCancer(_x.id)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        database!!.child("skinCancers").addValueEventListener(skinCancer_listener)
    }

    fun persistSkinCancer(ex: SkinCancer) {
        val _evo = SkinCancerVO(ex)
        val _key = _evo.getId()
        if (database == null) {
            return
        }
        database!!.child("skinCancers").child(_key).setValue(_evo)
    }

    fun deleteSkinCancer(ex: SkinCancer) {
        val _key: String = ex.id
        if (database == null) {
            return
        }
        database!!.child("skinCancers").child(_key).removeValue()
    }
}
