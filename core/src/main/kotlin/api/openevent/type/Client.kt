package api.openevent.type

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Example interface for future reference when creating clients
 * This interface is to be a model for future client designs.
 * It cannot be implemented by any client as Retrofit APIs are incompatible
 * with inheriting interfaces
 */
interface Client<K, in ID> {

    fun getAll(): Single<List<K>>

    fun get(id: ID): Single<K>

    fun post(item: K): Single<K>

    fun patch(item: K): Single<K>

    fun delete(id: ID): Completable

}
