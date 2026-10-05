# low-level-design

# Smart Room Fan Control System (LLD)

Low-Level Design implementation for a single-room ceiling fan controlled via a power switch and a speed regulator.

## Architectural Highlights
- **Strategy Pattern (`controlStrategy`):** Decouples knob position settings from device-specific speed mapping (RPM logic).
- **Dependency Injection:** Strategy and target `Appliance` injected via constructors.
- **State Preservation:** Stores target speed level regardless of power status (`isOn`).
- **Separation of Concerns:** Power control (`wallSwitch`) strictly isolated from input levels (`Regulator`).

## How to Run
```bash
javac Man.java
java Man