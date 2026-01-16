# ALIGNMENT: Docs to Markdown Conversion

## 1. Project Context
- **Target Directory**: `e:\Desktop\app\Dji_RC_Pro\Docs\Android_API\en`
- **Skill Location**: `e:\Desktop\app\Dji_RC_Pro\.claude\skills\docs2markdown`
- **Goal**: Convert HTML API documentation to Markdown, preserving structure and links.

## 2. Requirements Understanding
- **Input**: Recursive scan of HTML files in the target directory.
- **Output**: Markdown files (`.md`) generated alongside the HTML files (or in a mirrored structure).
- **Structure**: "Categorized by folder name" -> Preserving the existing directory hierarchy is the best interpretation to ensure links work and logical organization is maintained.
- **Link Handling**: Critical requirement. Internal links pointing to `.html` files must be rewritten to point to the corresponding `.md` files.
- **Tooling**: Use existing scripts `batch_convert.py` and `convert.py`.

## 3. Implementation Strategy
1.  **Verification**: Confirm `docs2markdown` library availability (Done).
2.  **Execution**: Run `batch_convert.py`.
    - This script recursively finds all HTML files.
    - It invokes `convert.py` for each file.
    - `convert.py` handles the actual conversion and link rewriting (`.html` -> `.md`).
3.  **Validation**: 
    - Verify that `.md` files are created.
    - Spot check a few files to ensure links are correctly transformed.
    - Check if `documentation_structure.json` exists for breadcrumbs (optional but good to know).

## 4. Uncertainties & Decisions
- **Structure JSON**: `convert.py` references `E:\Desktop\app\Dji_RC_Pro\Docs\documentation_structure.json`. I will check if this file exists. If not, breadcrumbs will be skipped, which is acceptable.
- **Output Location**: The scripts default to outputting in the same directory. This aligns with "categorized by folder name".

## 5. Plan
1.  Check for `documentation_structure.json`.
2.  Run `batch_convert.py`.
3.  Verify results.
