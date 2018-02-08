package la.kaelae.tvrecommendation

import android.support.annotation.IntDef

object JobIdManager {
  const val TYPE_CHANNEL_PROGRAMS = 1

  //16-1 for short. Adjust per your needs
  private const val JOB_TYPE_SHIFTS = 15

  @IntDef(value = [TYPE_CHANNEL_PROGRAMS.toLong()])
  @Retention(AnnotationRetention.SOURCE)
  annotation class JobType

  @JvmStatic
  fun getJobId(@JobType jobType: Int, objectId: Int): Int {
    if (0 < objectId && objectId < 1.shl(JOB_TYPE_SHIFTS)) {
      return jobType.shl(JOB_TYPE_SHIFTS) + objectId
    } else {
      val err = String.format("objectId $objectId must be between 0 and ${1.shl(JOB_TYPE_SHIFTS)}")
      throw IllegalArgumentException(err)
    }
  }
}