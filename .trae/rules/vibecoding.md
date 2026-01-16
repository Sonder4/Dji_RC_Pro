# Vibe Coding Rules & Workflow

## 1. Documentation Map
The following documents define the project context and must be referenced appropriately:

*   **`docs/context/Project_Context.md`**: **[CRITICAL]** The Source of Truth for project state.
    *   *When to read*: At the start of EVERY new session or complex task.
    *   *When to update*: After completing any task that changes the architecture, scope, or status.
*   **`prompts/System_Prompt.md`**: Defines role, coding standards, and behavior.
    *   *When to read*: When in doubt about coding style or error handling norms.
*   **`docs/development.md`**: Developer guide and project structure reference.
    *   *When to read*: When creating new files to ensure correct placement.
*   **`Docs/ERROR_LOG.md`**: Record of compilation and runtime errors.
    *   *When to read*: Before fixing a bug, check if a similar error occurred previously.
    *   *When to update*: After resolving any compilation or runtime error, record the error details, cause, and solution.
*   **`AGENTS.md`**: Available tools and skills.

## 2. Mandatory Workflow (The "Think-Discuss-Act" Loop)

For **every** user request, you MUST strictly follow this sequence:

### Step 1: Deep Analysis (Sequential Thinking)
*   **Tool**: `mcp_sequential-thinking_sequentialthinking`
*   **Action**: Analyze the user's request. Break it down.
    *   Check `docs/context/Project_Context.md` for alignment.
    *   Identify missing information.
    *   Formulate a hypothesis or plan.

### Step 2: Knowledge Retrieval (Context7 & Skills)
*   **Tool**: `mcp_context7_resolve-library-id` & `mcp_context7_query-docs`
*   **Skill**: `dji-msdk` (Use `openskills read dji-msdk`)
*   **Action**: 
    *   If the task involves external libraries (Android, Kotlin, etc.), **YOU MUST** query Context7.
    *   If the task involves **DJI MSDK**, **YOU MUST** use the `dji-msdk` skill to retrieve API details.
    *   *Do not guess APIs.*
    *   *Do not rely solely on internal training data if documentation is available.*

### Step 3: Proposal & Alignment (Feedback)
*   **Tool**: `mcp_mcp-feedback-enhanced_interactive_feedback`
*   **Action**: Present your **Analysis** (from Step 1) and **Plan** to the user.
    *   "Here is my analysis... Here is the plan... Do you agree?"
    *   **WAIT** for the user's response (or explicit approval in the tool result) before writing code.

### Step 4: Execution & Verification
*   **Tool**: `Write`, `RunCommand`, etc.
*   **Action**: Execute the plan.
    *   Follow strict coding standards (Kotlin, MVVM).
    *   **No TODOs** in code.

### Step 5: Documentation Sync
*   **Action**: Update `docs/context/Project_Context.md` to reflect the new state (e.g., move items from "Planned" to "Completed").

## 3. Vibe Coding Specifics
*   **Memory**: If you encounter a compilation error, record it in `Docs/ERROR_LOG.md`.
*   **Context**: Maintain the integrity of `Project_Context.md`. It is your long-term memory.
