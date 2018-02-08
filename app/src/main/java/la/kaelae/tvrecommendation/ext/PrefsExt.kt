package la.kaelae.tvrecommendation.ext

import android.content.Context
import la.kaelae.tvrecommendation.R

private const val DEFAULT_CHANNEL_ID_KEY = "default_channel_id"
private const val DELETED_IDS = "deleted_ids"

fun Context.saveDefaultChannelId(channelId: Long) {
  val editor = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE).edit()
  editor.putLong(DEFAULT_CHANNEL_ID_KEY, channelId).apply()
}

fun Context.loadDefaultChannelId(): Long {
  return getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
      .getLong(DEFAULT_CHANNEL_ID_KEY, -1L)
}

fun Context.saveDeletedIds(ids: List<String>) {
  val editor = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE).edit()
  editor.putString(DELETED_IDS, ids.joinToString(",")).apply()
}

fun Context.loadDeletedIds(): List<String> {
  val idsStr = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
      .getString(DELETED_IDS, "")
  return idsStr.split(",")
}

fun Context.clearDeletedIds() {
  val editor = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE).edit()
  editor.remove(DELETED_IDS).apply()
}