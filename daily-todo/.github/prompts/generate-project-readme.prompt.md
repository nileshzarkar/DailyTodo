---
description: "Generate README.md for the DailyTodo project"
name: "Generate README"
argument-hint: "Provide project summary, key features, run steps, API details, and any assumptions"
agent: "agent"
---
Generate a complete README.md at the repository root for this DailyTodo project.

Input provided by the user may include:
- Project summary
- Core features
- Tech stack
- Local setup and run commands
- Build/test commands
- API endpoints and usage examples
- UI pages or workflows
- Known limitations or assumptions

Follow these rules while generating the README:
- Keep existing project context accurate (Quarkus, Java 21, H2, Maven Wrapper).
- Include practical sections for onboarding a new developer.
- If endpoints are available, document method, path, and purpose.
- Do not fabricate endpoints or behaviors; document only what can be verified from repository files.
- If exact details are missing, infer from repository files and keep assumptions explicit.
- Keep wording concise and practical.

Output requirements:
- Create README.md at the repository root as the primary output of this prompt.
- If README.md already exists, update it with the newly generated content while preserving any clearly project-specific content that remains valid.
- Ensure the README contains at minimum:
  - Project Overview
  - Tech Stack
  - Features
  - Project Structure
  - Run Locally
  - Build and Test
  - API Overview
  - Notes / Limitations
- Include a short summary of what was generated or changed.
