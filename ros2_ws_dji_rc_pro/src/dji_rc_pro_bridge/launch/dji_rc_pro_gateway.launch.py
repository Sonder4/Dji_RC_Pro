from launch import LaunchDescription
from launch.actions import DeclareLaunchArgument
from launch.substitutions import LaunchConfiguration, PathJoinSubstitution
from launch_ros.actions import Node
from launch_ros.substitutions import FindPackageShare


def generate_launch_description():
    control_port_arg = DeclareLaunchArgument('control_port', default_value='1387')
    discovery_port_arg = DeclareLaunchArgument('discovery_port', default_value='1388')
    discovery_group_arg = DeclareLaunchArgument('discovery_group', default_value='ff12::2026')
    pair_code_arg = DeclareLaunchArgument('pair_code', default_value='NCURC2026')
    transport_mode_arg = DeclareLaunchArgument('transport_mode', default_value='udp_only')
    lease_ms_arg = DeclareLaunchArgument('lease_ms', default_value='5000')
    require_ready_arg = DeclareLaunchArgument('require_ready_for_pairing', default_value='false')
    host_id_arg = DeclareLaunchArgument('host_id', default_value='')
    host_name_arg = DeclareLaunchArgument('host_name', default_value='')
    cmd_vel_topic_arg = DeclareLaunchArgument('cmd_vel_topic', default_value='/dji_rc_pro_bridge/cmd_vel')
    joy_topic_arg = DeclareLaunchArgument('joy_topic', default_value='/dji_rc_pro_bridge/joy')
    raw_topic_arg = DeclareLaunchArgument('raw_topic', default_value='/dji_rc_pro_bridge/chassis_ctrl_raw')
    status_topic_arg = DeclareLaunchArgument('status_topic', default_value='/mcu_comm_node/status')
    ble_frame_topic_arg = DeclareLaunchArgument('ble_frame_topic', default_value='/dji_rc_pro_bridge/ble/control_frame')
    transport_status_topic_arg = DeclareLaunchArgument('transport_status_topic', default_value='/dji_rc_pro_bridge/transport_status')
    ble_fallback_holdoff_arg = DeclareLaunchArgument('ble_fallback_holdoff_ms', default_value='1500')
    teleop_config_file_arg = DeclareLaunchArgument(
        'teleop_config_file',
        default_value=PathJoinSubstitution([
            FindPackageShare('dji_rc_pro_bridge'),
            'config',
            'dji_rc_pro_joy.config.yaml',
        ]),
    )

    gateway_node = Node(
        package='dji_rc_pro_bridge',
        executable='dji_rc_pro_gateway',
        name='dji_rc_pro_gateway',
        output='screen',
        parameters=[{
            'control_port': LaunchConfiguration('control_port'),
            'discovery_port': LaunchConfiguration('discovery_port'),
            'discovery_group': LaunchConfiguration('discovery_group'),
            'pair_code': LaunchConfiguration('pair_code'),
            'transport_mode': LaunchConfiguration('transport_mode'),
            'lease_ms': LaunchConfiguration('lease_ms'),
            'require_ready_for_pairing': LaunchConfiguration('require_ready_for_pairing'),
            'host_id': LaunchConfiguration('host_id'),
            'host_name': LaunchConfiguration('host_name'),
            'cmd_vel_topic': LaunchConfiguration('cmd_vel_topic'),
            'joy_topic': LaunchConfiguration('joy_topic'),
            'raw_topic': LaunchConfiguration('raw_topic'),
            'status_topic': LaunchConfiguration('status_topic'),
            'ble_frame_topic': LaunchConfiguration('ble_frame_topic'),
            'transport_status_topic': LaunchConfiguration('transport_status_topic'),
            'ble_fallback_holdoff_ms': LaunchConfiguration('ble_fallback_holdoff_ms'),
        }],
    )

    joy_mapper_node = Node(
        package='dji_rc_pro_bridge',
        executable='dji_rc_pro_joy_to_cmd_vel',
        name='dji_rc_pro_joy_to_cmd_vel',
        output='screen',
        parameters=[
            LaunchConfiguration('teleop_config_file'),
            {
                'joy_topic': LaunchConfiguration('joy_topic'),
                'cmd_vel_topic': LaunchConfiguration('cmd_vel_topic'),
            },
        ],
    )

    return LaunchDescription([
        control_port_arg,
        discovery_port_arg,
        discovery_group_arg,
        pair_code_arg,
        transport_mode_arg,
        lease_ms_arg,
        require_ready_arg,
        host_id_arg,
        host_name_arg,
        cmd_vel_topic_arg,
        joy_topic_arg,
        raw_topic_arg,
        status_topic_arg,
        ble_frame_topic_arg,
        transport_status_topic_arg,
        ble_fallback_holdoff_arg,
        teleop_config_file_arg,
        gateway_node,
        joy_mapper_node,
    ])
