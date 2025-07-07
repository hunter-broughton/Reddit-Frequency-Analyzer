#!/bin/bash

echo "GitHub Release Upload Guide"
echo "==========================="
echo ""
echo "📋 Steps to create the release:"
echo ""
echo "1. Go to: https://github.com/hunter-broughton/reddit-word-frequency-analyzer/releases/new"
echo ""
echo "2. Fill in the release form:"
echo "   - Tag version: v1.0"
echo "   - Release title: Reddit Word Frequency Analyzer v1.0"
echo "   - Description: Copy content from RELEASE_NOTES.md"
echo ""
echo "3. Upload these files (drag and drop into the attachments area):"
echo ""

if [ -f "reddit-data-small.zip" ]; then
    echo "   ✅ reddit-data-small.zip ($(du -h reddit-data-small.zip | cut -f1))"
else
    echo "   ❌ reddit-data-small.zip (missing - run ./package_data.sh first)"
fi

if [ -f "reddit-data-medium.zip" ]; then
    echo "   ✅ reddit-data-medium.zip ($(du -h reddit-data-medium.zip | cut -f1))"
else
    echo "   ❌ reddit-data-medium.zip (missing - run ./package_data.sh first)"
fi

if [ -f "reddit-data-complete.zip" ]; then
    echo "   ✅ reddit-data-complete.zip ($(du -h reddit-data-complete.zip | cut -f1))"
else
    echo "   ❌ reddit-data-complete.zip (missing - run ./package_data.sh first)"
fi

echo ""
echo "4. Individual data files (optional, for direct download):"
for file in reddit_comments_*.txt; do
    if [ -f "$file" ] && [[ ! "$file" =~ _written ]]; then
        echo "   📄 $file ($(du -h "$file" | cut -f1))"
    fi
done

echo ""
echo "5. Click 'Publish release'"
echo ""
echo "🔗 After publishing, update README.md links to point to:"
echo "   https://github.com/hunter-broughton/reddit-word-frequency-analyzer/releases/download/v1.0/[filename]"
echo ""
echo "📝 Don't forget to:"
echo "   - Test the download links work"
echo "   - Update any documentation that references the old Git LFS approach"
echo "   - Consider pinning the release for visibility"
