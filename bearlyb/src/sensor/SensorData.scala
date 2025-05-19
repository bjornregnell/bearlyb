package bearlyb.sensor

enum SensorData:
  /** Returned for an invalid sensor */
  case Invalid
  /** Unknown sensor type */
  case Unknown
  /** Accelerometer */
  case Accel(x: Float, y: Float, z: Float)
  /** Gyroscope */
  case Gyro(pitch: Float, yaw: Float, roll: Float)
  /** Accelerometer for left Joy-Con controller and Wii nunchuk */
  case AccelL(x: Float, y: Float, z: Float)
  /** Gyroscope for left Joy-Con controller */
  case GyroL(pitch: Float, yaw: Float, roll: Float)
  /** Accelerometer for right Joy-Con controller */
  case AccelR(x: Float, y: Float, z: Float)
  /** Gyroscope for right Joy-Con controller */
  case GyroR(pitch: Float, yaw: Float, roll: Float)