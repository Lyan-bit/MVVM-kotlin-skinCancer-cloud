package com.example.skincancer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.skincancer.model.SkinCancer
import com.example.skincancer.SkinCancerVO
import com.example.skincancer.database.FirebaseDB
import java.util.ArrayList

class SkinViewModel constructor(context: Context): ViewModel() {
    private var cdb: FirebaseDB = FirebaseDB.getInstance()

    companion object {
        private var instance: SkinViewModel? = null
        fun getInstance(context: Context): SkinViewModel {
            return instance ?: SkinViewModel(context)
        }
    }

    /* This metatype code requires OclType.java, OclAttribute.java, OclOperation.java */
    fun initialiseOclTypes() {
        val skinCancerOclType: OclType = OclType.createByPKOclType("SkinCancer")
        skinCancerOclType.setMetatype(SkinCancer::class.java)
    }

    private var currentSkinCancer: SkinCancerVO? = null
    private var currentSkinCancers: ArrayList<SkinCancerVO> = ArrayList()

    fun listSkinCancer(): ArrayList<SkinCancerVO> {
        val skinCancers: ArrayList<SkinCancer> = SkinCancer.SkinCancerAllInstances
        currentSkinCancers.clear()
        for (i in skinCancers.indices) {
            currentSkinCancers.add(SkinCancerVO(skinCancers[i]))
        }
        return currentSkinCancers
    }

    fun stringListSkinCancer(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentSkinCancers.indices) {
            res.add(currentSkinCancers[x].toString() + "")
        }
        return res
    }

    fun getSkinCancerByPK(value: String): SkinCancer? {
        return SkinCancer.SkinCancerIndex[value]
    }

    fun retrieveSkinCancer(value: String): SkinCancer? {
        return getSkinCancerByPK(value)
    }

    fun allSkinCancerids(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentSkinCancers.indices) {
            res.add(currentSkinCancers[x].getId() + "")
        }
        return res
    }

    fun allSkinCancerdates(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentSkinCancers.indices) {
            res.add(currentSkinCancers[x].getDates() + "")
        }
        return res
    }

    fun allSkinCancerimages(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentSkinCancers.indices) {
            res.add(currentSkinCancers[x].getImages() + "")
        }
        return res
    }

    fun allSkinCanceroutcomes(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentSkinCancers.indices) {
            res.add(currentSkinCancers[x].getOutcome() + "")
        }
        return res
    }

    fun setSelectedSkinCancer(x: SkinCancerVO) {
        currentSkinCancer = x
    }

    fun setSelectedSkinCancer(i: Int) {
        if (i < currentSkinCancers.size) {
            currentSkinCancer = currentSkinCancers[i]
        }
    }

    fun getSelectedSkinCancer(): SkinCancerVO? {
        return currentSkinCancer
    }

    fun persistSkinCancer(x: SkinCancer) {
        val vo = SkinCancerVO(x)
        cdb.persistSkinCancer(x)
        currentSkinCancer = vo
    }

    fun editSkinCancer(x: SkinCancerVO) {
        var obj = getSkinCancerByPK(x.getId())
        if (obj == null) {
            obj = SkinCancer.createByPKSkinCancer(x.getId())
        }
        obj.id = x.getId()
        obj.dates = x.getDates()
        obj.images = x.getImages()
        obj.outcome = x.getOutcome()
        cdb.persistSkinCancer(obj)
        currentSkinCancer = x
    }

    fun createSkinCancer(x: SkinCancerVO) {
        editSkinCancer(x)
    }

    fun deleteSkinCancer(id: String) {
        val obj = getSkinCancerByPK(id)
        if (obj != null) {
            cdb.deleteSkinCancer(obj)
            SkinCancer.killSkinCancer(id)
        }
        currentSkinCancer = null
    }

    fun searchSkinCancerid(id: String) : ArrayList<SkinCancer> {
        var itemsList = ArrayList<SkinCancer>()
        for (x in currentSkinCancers.indices) {
            if ( currentSkinCancers[x].getId() == id) {
                val vo: SkinCancerVO = currentSkinCancers[x]
                val itemx = SkinCancer.createByPKSkinCancer(vo.getId())
                itemx.id = vo.getId()
                itemx.dates = vo.getDates()
                itemx.images = vo.getImages()
                itemx.outcome = vo.getOutcome()
                itemsList.add(itemx)
            }
        }
        return itemsList
    }

    fun searchSkinCancerdates(dates: String) : ArrayList<SkinCancer> {
        var itemsList = ArrayList<SkinCancer>()
        for (x in currentSkinCancers.indices) {
            if ( currentSkinCancers[x].getDates() == dates) {
                val vo: SkinCancerVO = currentSkinCancers[x]
                val itemx = SkinCancer.createByPKSkinCancer(vo.getId())
                itemx.id = vo.getId()
                itemx.dates = vo.getDates()
                itemx.images = vo.getImages()
                itemx.outcome = vo.getOutcome()
                itemsList.add(itemx)
            }
        }
        return itemsList
    }

    fun searchSkinCancerimage(image: String) : ArrayList<SkinCancer> {
        var itemsList = ArrayList<SkinCancer>()
        for (x in currentSkinCancers.indices) {
            if ( currentSkinCancers[x].getImages() == image) {
                val vo: SkinCancerVO = currentSkinCancers[x]
                val itemx = SkinCancer.createByPKSkinCancer(vo.getId())
                itemx.id = vo.getId()
                itemx.dates = vo.getDates()
                itemx.images = vo.getImages()
                itemx.outcome = vo.getOutcome()
                itemsList.add(itemx)
            }
        }
        return itemsList
    }

    fun searchSkinCanceroutcome(outcome: String) : ArrayList<SkinCancer> {
        var itemsList = ArrayList<SkinCancer>()
        for (x in currentSkinCancers.indices) {
            if ( currentSkinCancers[x].getOutcome() == outcome) {
                val vo: SkinCancerVO = currentSkinCancers[x]
                val itemx = SkinCancer.createByPKSkinCancer(vo.getId())
                itemx.id = vo.getId()
                itemx.dates = vo.getDates()
                itemx.images = vo.getImages()
                itemx.outcome = vo.getOutcome()
                itemsList.add(itemx)
            }
        }
        return itemsList
    }

}

//    private var dbm: FirebaseDB = FirebaseDB ()
//    private var currentSkin: SkinCancerVO? = null
//    private var currentSkins: ArrayList<SkinCancerVO> = ArrayList()
//
//    companion object {
//        private var instance: SkinViewModel? = null
//        fun getInstance(context: Context): SkinViewModel {
//            return instance ?: SkinViewModel(context)
//        }
//    }
//
//    fun stringListSkin(): ArrayList<String> {
//        currentSkins = dbm.listSkin()
//        val res: ArrayList<String> = ArrayList()
//        for (x in currentSkins.indices) {
//            res.add(currentSkins[x].toString())
//        }
//        return res
//    }
//
//    fun getSkinByPK(_val: String): SkinCancer? {
//        val res: List<SkinCancerVO> = dbm.searchBySkinid(_val)
//        return if (res.isEmpty()) {
//            null
//        } else {
//            val vo: SkinCancerVO = res[0]
//            val itemx = SkinCancer.createByPKSkinCancer(_val)
//            itemx.id = vo.getId()
//            itemx.dates = vo.getDates()
//            itemx.images = vo.getImages()
//            itemx.outcome = vo.getOutcome()
//            itemx
//        }
//    }
//
//    fun retrieveSkin(_val: String): SkinCancer? {
//        return getSkinByPK(_val)
//    }
//
//    fun allSkinids(): ArrayList<String> {
//        currentSkins = dbm.listSkin()
//        val res: ArrayList<String> = ArrayList()
//        for (skin in currentSkins.indices) {
//            res.add(currentSkins[skin].getId())
//        }
//        return res
//    }
//
//    fun allSkinoutcomes(): ArrayList<String> {
//        currentSkins = dbm.listSkin()
//        val res: ArrayList<String> = ArrayList()
//        for (skin in currentSkins.indices) {
//            res.add(currentSkins[skin].getOutcome())
//        }
//        return res
//    }
//
//    fun allSkinimages(): ArrayList<String> {
//        currentSkins = dbm.listSkin()
//        val res: ArrayList<String> = ArrayList()
//        for (skin in currentSkins.indices) {
//            res.add(currentSkins[skin].getImages())
//        }
//        return res
//    }
//
//    fun allSkindates(): ArrayList<String> {
//        currentSkins = dbm.listSkin()
//        val res: ArrayList<String> = ArrayList()
//        for (skin in currentSkins.indices) {
//            res.add(currentSkins[skin].getDates())
//        }
//        return res
//    }
//
//    fun setSelectedSkin(x: SkinCancerVO) {
//        currentSkin = x
//    }
//
//    fun setSelectedSkin(i: Int) {
//        if (i < currentSkins.size) {
//            currentSkin = currentSkins[i]
//        }
//    }
//
//    fun getSelectedSkin(): SkinCancerVO? {
//        return currentSkin
//    }
//
//    fun persistSkin(_x: SkinCancer) {
//        val vo = SkinCancerVO(_x)
//        dbm.editSkin(vo)
//        currentSkin = vo
//    }
//
//    fun listSkin(): ArrayList<SkinCancerVO> {
//        currentSkins = dbm.listSkin()
//        return currentSkins
//    }
//
//    fun editSkin(_x: SkinCancerVO) {
//        dbm.editSkin(_x)
//        currentSkin = _x
//    }
//
//    fun createSkin(_x: SkinCancerVO) {
//        dbm.createSkin(_x)
//        currentSkin = _x
//    }
//
//    fun deleteSkin(_id: String) {
//        dbm.deleteSkin(_id)
//        currentSkin = null
//    }
//
//    fun searchBySkinid(idx: String): List<SkinCancerVO> {
//        currentSkins = dbm.searchBySkinid(idx)
//        return currentSkins
//    }
//
//    fun searchBySkindate(datex: String): List<SkinCancerVO> {
//        currentSkins = dbm.searchBySkindate(datex)
//        return currentSkins
//    }
//
//    fun searchBySkinimage(imagex: String): List<SkinCancerVO> {
//        currentSkins = dbm.searchBySkinimage(imagex)
//        return currentSkins
//    }
//
//    fun searchBySkinoutcome(outcomex: String): List<SkinCancerVO> {
//        currentSkins = dbm.searchBySkinoutcome(outcomex)
//        return currentSkins
//    }
//
//}