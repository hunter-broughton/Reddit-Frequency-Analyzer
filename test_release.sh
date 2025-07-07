#!/bin/bash

echo "Testing GitHub Release Downloads"
echo "==============================="
echo ""

REPO_URL="https://github.com/hunter-broughton/Reddit-Frequency-Analyzer"
RELEASE_TAG="v1.0"

echo "🔗 Your release should be visible at:"
echo "   $REPO_URL/releases/tag/$RELEASE_TAG"
echo ""

echo "📦 Your data files should be downloadable from:"
echo "   $REPO_URL/releases/download/$RELEASE_TAG/reddit-data-small.zip"
echo "   $REPO_URL/releases/download/$RELEASE_TAG/reddit-data-medium.zip" 
echo "   $REPO_URL/releases/download/$RELEASE_TAG/reddit-data-complete.zip"
echo ""

echo "🧪 To test the links, try:"
echo "   curl -I $REPO_URL/releases/download/$RELEASE_TAG/reddit-data-small.zip"
echo ""

echo "✅ If you see 'HTTP/2 200' or 'HTTP/2 302', the files are accessible!"
echo "❌ If you see 'HTTP/2 404', check that the release was published successfully."
