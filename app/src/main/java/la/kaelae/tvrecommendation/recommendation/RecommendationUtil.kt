package la.kaelae.tvrecommendation.recommendation

import android.content.ContentResolver
import android.support.media.tv.Channel
import android.support.media.tv.PreviewProgram
import android.support.media.tv.TvContractCompat
import android.support.media.tv.WatchNextProgram

fun loadChannel(cr: ContentResolver,
    projection: Array<String>? = arrayOf(),
    selection: String? = "",
    selectionArgs: Array<String>? = arrayOf(),
    sortOrder: String? = ""): Channel {
  val cursor = cr.query(TvContractCompat.Channels.CONTENT_URI, projection, selection, selectionArgs,
      sortOrder)
  return Channel.fromCursor(cursor)
}

fun insertChannel(cr: ContentResolver, channel: Channel) {
  cr.insert(TvContractCompat.Channels.CONTENT_URI, channel.toContentValues())
}

fun bulkInsertChannels(cr: ContentResolver, channels: List<Channel>) {
  channels.map { it.toContentValues() }
      .toList()
      .let {
        cr.bulkInsert(TvContractCompat.Channels.CONTENT_URI, it.toTypedArray())
      }
}

fun updateChanel(cr: ContentResolver, channel: Channel) {
  cr.update(TvContractCompat.buildChannelUri(channel.id), channel.toContentValues(), null, null)
}

fun deleteChannel(cr: ContentResolver, channel: Channel) {
  cr.delete(TvContractCompat.buildChannelUri(channel.id), null, null)
}

fun loadPrograms(cr: ContentResolver,
    channelId: Long,
    projection: Array<String>? = arrayOf(),
    selection: String? = "",
    selectionArgs: Array<String>? = arrayOf(),
    sortOrder: String? = ""): List<PreviewProgram> {
  val cursor = cr.query(TvContractCompat.PreviewPrograms.CONTENT_URI,
      projection, selection, selectionArgs, sortOrder)
  cursor ?: return emptyList()
  cursor.moveToFirst()
  val programs = ArrayList<PreviewProgram>()
  do {
    val program = PreviewProgram.fromCursor(cursor)
    if (program.channelId != channelId) continue
    programs.add(program)
  } while (cursor.moveToNext())
  return programs
}

fun insertProgram(cr: ContentResolver, program: PreviewProgram) {
  cr.insert(TvContractCompat.PreviewPrograms.CONTENT_URI, program.toContentValues())
}

fun bulkInsertPrograms(cr: ContentResolver, programs: List<PreviewProgram>) {
  programs.map { it.toContentValues() }
      .let {
        cr.bulkInsert(TvContractCompat.PreviewPrograms.CONTENT_URI, it.toTypedArray())
      }
}

fun updateProgram(cr: ContentResolver, program: PreviewProgram) {
  cr.update(TvContractCompat.buildPreviewProgramUri(program.id),
      program.toContentValues(), null, null)
}

fun deleteProgram(cr: ContentResolver, program: PreviewProgram) {
  cr.delete(TvContractCompat.buildPreviewProgramUri(program.id), null, null)
}

fun deleteProgramsByChannel(cr: ContentResolver, channelId: Long) {
  cr.delete(TvContractCompat.buildChannelUri(channelId), null, null)
}

fun deleteAllPrograms(cr: ContentResolver) {
  cr.delete(TvContractCompat.PreviewPrograms.CONTENT_URI, null, null)
}

fun insertProgramToWatchNext(cr: ContentResolver, program: WatchNextProgram) {
  cr.insert(TvContractCompat.WatchNextPrograms.CONTENT_URI, program.toContentValues())
}

fun bulkInsertProgramsToWatchNext(cr: ContentResolver, programs: List<WatchNextProgram>) {
  programs.map { it.toContentValues() }
      .let {
        cr.bulkInsert(TvContractCompat.WatchNextPrograms.CONTENT_URI, it.toTypedArray())
      }
}

fun deleteWatchNextProgram(cr: ContentResolver, program: WatchNextProgram) {
  cr.delete(TvContractCompat.buildWatchNextProgramUri(program.id), null, null)
}

fun deleteWatchNextPrograms(cr: ContentResolver) {
  cr.delete(TvContractCompat.WatchNextPrograms.CONTENT_URI, null, null)
}