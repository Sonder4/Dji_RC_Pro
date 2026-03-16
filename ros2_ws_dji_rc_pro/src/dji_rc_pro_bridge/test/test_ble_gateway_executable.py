import os
from pathlib import Path


MODULE_PATH = Path(__file__).resolve().parents[1] / "src" / "tools" / "dji_rc_pro_ble_gateway.py"


def test_ble_gateway_script_is_executable():
    assert MODULE_PATH.exists()
    assert os.access(MODULE_PATH, os.X_OK)
