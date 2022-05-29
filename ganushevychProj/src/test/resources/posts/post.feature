
Feature: Post Feature

  Background:
    Given User opens 'Home' page

    @R004
    @BeforeDeletingAllPostsForDefaultUser
    @AfterDeletingAllPostsForDefaultUser
    Scenario: R004 Check number of posts
      And Create 2 posts via API for 'default' user and 'default' password
      When User click on 'Profile' button on 'Home' page
      Then User is redirect to Profile page
      And User sees 2 posts in Post list on Profile page

