#!/usr/bin/env python3

# python-can documentation:
# http://python-can.readthedocs.io/en/latest/api.html
# gpsd interface with callback mod:
# https://github.com/keigezellig/gps3

from gps3.agps3threaded import AGPS3mechanism
import logging
import struct
import sys
import can

# Configure the logger, get our logger, silence the python-can lib logger.
logging.basicConfig()
log = logging.getLogger(__name__)
log.setLevel(logging.INFO)
logging.getLogger('can').setLevel(logging.FATAL)


class GpsCanDemo:
    """Demo how to send CAN messages containing GPS data."""
    mode = 0
    lat = 0.0
    lon = 0.0
    speed = 0.0

    __status_dict__ = {0: 'Unknown', 1: 'No Fix', 2: '2D', 3: '3D'}

    def gps_callback(self, data_stream):
        # Try parsing latitude, longitude and the fix mode if available
        try:
            self.mode = int(data_stream.mode)
            self.lat = float(data_stream.lat)
            self.lon = float(data_stream.lon)
            self.speed = float(data_stream.speed)
        except ValueError:
            log.info("mode:{} lat:{} lon:{} speed:{}".format(self.__status_dict__[self.mode], data_stream.lat, data_stream.lon, data_stream.speed))
            return

        # Pack the python float into a little endian 8 byte double and update the message data
        self.msg_lat.data = bytearray(struct.pack("<d", self.lat))
        self.msg_lon.data = bytearray(struct.pack("<d", self.lon))
        self.msg_speed.data = bytearray(struct.pack("<d", self.speed))

        # Send the data
        try:
            self.bus.send(self.msg_lat)
            self.bus.send(self.msg_lon)
            self.bus.send(self.msg_speed)
        except can.CanError:
            log.error("Could not transmit!")
        log.info("mode:{} lat:{} lon:{} speed:{}".format(self.__status_dict__[self.mode], self.lat, self.lon, self.speed))

    def __init__(self):
        # Creates the can bus.
        # Unless you are running Python3.3 or lower the recommended backend is socketcan_native.
        # For Python2.7 and Python3 < 3.4, the available backend is socketcan_ctypes.
        self.channel = 'can0'
        if sys.version_info[0] >= 3 and sys.version_info[1] >= 4:
            self.bustype = 'socketcan_native'
        else:
            self.bustype = 'socketcan_ctypes'
        self.bus = can.interface.Bus(channel=self.channel, bustype=self.bustype)

        # Create GPSD listener thread with callback and 1s sleep period
        self.agps_thread = AGPS3mechanism()
        self.agps_thread.stream_data(on_datareceived=self.gps_callback)
        self.agps_thread.run_thread(usnap=1)

        # Create can messages
        self.msg_lat = can.Message(arbitration_id=0x512)
        self.msg_lon = can.Message(arbitration_id=0x513)
        self.msg_speed = can.Message(arbitration_id=0x514)


if __name__ == '__main__':
    GpsCanDemo()
    try:
        input = raw_input
    except NameError:
        pass
    input()
